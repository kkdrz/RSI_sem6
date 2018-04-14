package farmer;

import java.io.Serializable;
import java.util.Objects;

public class MyCustomObject implements Comparable<MyCustomObject>, Serializable {

    private static final long serialVersionUID = 99L;

    private String el1;
    private Integer el2;

    public MyCustomObject(String el1, Integer el2) {
        this.el1 = el1;
        this.el2 = el2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyCustomObject that = (MyCustomObject) o;
        return Objects.equals(el1, that.el1) &&
                Objects.equals(el2, that.el2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(el1, el2);
    }

    public String getEl1() {
        return el1;
    }

    public void setEl1(String el1) {
        this.el1 = el1;
    }

    public Integer getEl2() {
        return el2;
    }

    public void setEl2(Integer el2) {
        this.el2 = el2;
    }

    @Override
    public int compareTo(MyCustomObject o) {
        if (el1.compareTo(o.el1) == 0) {
            return el2.compareTo(o.el2);
        }
        return el1.compareTo(o.el1);
    }

    @Override
    public String toString() {
        return  el1 + el2;
    }
}
