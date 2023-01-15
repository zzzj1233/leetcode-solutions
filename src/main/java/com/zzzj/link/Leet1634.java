package com.zzzj.link;

public class Leet1634 {

    private static class PolyNode {
        int coefficient, power;
        PolyNode next = null;

        PolyNode() {
        }

        PolyNode(int x, int y) {
            this.coefficient = x;
            this.power = y;
        }

        PolyNode(int x, int y, PolyNode next) {
            this.coefficient = x;
            this.power = y;
            this.next = next;
        }
    }

    public static PolyNode addPoly(PolyNode poly1, PolyNode poly2) {
        // 项数高的必须在前面
        return add(poly1, poly2);
    }

    public static PolyNode add(PolyNode cur, PolyNode other) {
        if (other == null) {
            return cur;
        }
        if (cur == null) {
            return other;
        }
        if (cur.power == other.power) {
            cur.coefficient += other.coefficient;

            if (cur.coefficient == 0) {
                return addPoly(cur.next, other.next);
            } else {
                cur.next = addPoly(cur.next, other.next);
                return cur;
            }
        } else if (cur.power < other.power) {
            other.next = addPoly(cur, other.next);
            return other;
        } else {
            cur.next = addPoly(cur.next, other);
            return cur;
        }
    }

}
