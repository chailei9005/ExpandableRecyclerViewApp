package chailei.com.expandablerecyclerviewapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;

import java.util.ArrayList;
import java.util.List;

import chailei.com.expandablerecyclerviewapp.adapters.RecyclerAdapter;
import chailei.com.expandablerecyclerviewapp.entitys.Tree;

public class MainActivity extends AppCompatActivity {

//    private List<String> list;
    private PullToRefreshRecyclerView recycler;
    private List<Tree<?>> trees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);

        recycler = (PullToRefreshRecyclerView) findViewById(R.id.recycler);
//        list = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            list.add(String.format("第%02d分组",i));
//        }
//        recycler.setAdapter(new RecyclerAdapter(this,list));

        trees = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Tree<String> tree = new Tree<>(String.format("第1层第%02d个分组",i));
            for (int j = 0; j < 10; j++) {
                Tree<String> tree_child = new Tree<>(String.format("第2层第%02d个分组", j));
                tree.addChild(tree_child);
                for (int k = 0; k < 10; k++) {
                    Tree<String> tree_child_child = new Tree<>(String.format("第3层第%02d个分组", k));
//                    tree_child.getList().add(tree_child_child);
                    tree_child.addChild(tree_child_child);
                }
            }
            trees.add(tree);
        }

//        recycler.setAdapter(new RecyclerAdapter(this,trees));
        RecyclerView recyclerView = recycler.getRefreshableView();
        recycler.setMode(PullToRefreshBase.Mode.BOTH);
        recyclerView.setAdapter(new RecyclerAdapter(this,trees));


    }
}
