package com.csm.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class QuadTree {
    @Getter
    public static class Point {
        double x;
        double y;
        Long id;

        public Point(double x, double y, Long id) {
            this.x = x;
            this.y = y;
            this.id = id;
        }

        @Override
        public String toString() {
            return "Point [x:" + x + ", y:" + y + "]";
        }
    }

    @Getter
    @Setter
    public static class BoundingBox {
        double xMin, yMin, xMax, yMax;

        public BoundingBox(double xMin, double yMin, double xMax, double yMax) {
            this.xMin = xMin;
            this.yMin = yMin;
            this.xMax = xMax;
            this.yMax = yMax;
        }

        public boolean contains(Point point) {
            if (point.x > xMax || point.x < xMin) {
                return false;
            }
            if (point.y > yMax || point.y < yMin) {
                return false;
            }
            return true;
        }

        public boolean intersects(BoundingBox other) {
            return !(other.xMin > xMax || other.xMax < xMin || other.yMin > yMax || other.yMax < yMin);
        }

        @Override
        public String toString() {
            return "Boundary: [xMin: " + xMin + ", xMax: " + xMax + ", yMin: " + yMin + ", yMax: " + yMax + "]";
        }
    }

    private static final int MAX_CAPACITY = 4;
    private BoundingBox boundary;
    private List<Point> points;

    private QuadTree topLeft;
    private QuadTree topRight;
    private QuadTree botLeft;
    private QuadTree botRight;

    public QuadTree(BoundingBox boundary) {
        this.boundary = boundary;
        this.points = new ArrayList<Point>();
    }

    public void subdivide() {
        double xMid = (boundary.xMin + boundary.xMax) / 2;
        double yMid = (boundary.yMin + boundary.yMax) / 2;

        topLeft = new QuadTree(new BoundingBox(xMid, boundary.yMin, boundary.xMax, yMid));
        topRight = new QuadTree(new BoundingBox(xMid, yMid, boundary.xMax, boundary.yMax));
        botLeft = new QuadTree(new BoundingBox(boundary.xMin, boundary.yMin, xMid, yMid));
        botRight = new QuadTree(new BoundingBox(boundary.xMin, yMid, xMid, boundary.yMax));
    }

    public boolean insert(Point point) {
        if (!boundary.contains(point)) {
            return false;
        }

        if (points.size() < MAX_CAPACITY) {
            points.add(point);
            return true;
        }

        if (topLeft == null) {
            subdivide();
        }

        if (topLeft.insert(point)) return true;
        if (topRight.insert(point)) return true;
        if (botLeft.insert(point)) return true;
        if (botRight.insert(point)) return true;

        return false;
    }

    public List<Point> query(BoundingBox range, List<Point> found) {
        if (!boundary.intersects(range)) {
            return new ArrayList<>(points);
        }

        for (Point point : points) {
            if (range.contains(point)) {
                found.add(point);
            }
        }

        if (topLeft != null) {
            topLeft.query(range, found);
            topRight.query(range, found);
            botLeft.query(range, found);
            botRight.query(range, found);
        }

        return found;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("QuadTree: ").append(boundary);
        for (Point point : points) {
            sb.append("\n").append(point);
        }
        if (topLeft != null) {
            sb.append("\nTopLeft: ").append(topLeft);
            sb.append("\nTopRight: ").append(topRight);
            sb.append("\nBotLeft: ").append(botLeft);
            sb.append("\nBotRight: ").append(botRight);
        }
        return sb.toString();
    }

}
