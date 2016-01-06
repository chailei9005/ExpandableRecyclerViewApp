package chailei.com.expandablerecyclerviewapp.entitys;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 16-1-5.
 */
public class Tree<T> {

    private int level;
    private T data;
    private List<Tree<?>> child;
    private boolean isExpand = false;
    private boolean isSelected = false;

    public Tree(T data) {
        this.data = data;
            child = new ArrayList<>();
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public boolean isExpand() {
        return isExpand;
    }

    public void setIsExpand(boolean isExpand) {
        this.isExpand = isExpand;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<Tree<?>> getList() {
        return child;
    }

    public void setList(List<Tree<?>> child) {
        this.child = child;
    }

    public void addChild(Tree<?> children){

        children.setLevel(level+1);
        child.add(children);
    }
}
