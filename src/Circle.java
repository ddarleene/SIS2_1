class Circle extends Main {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return 3.14 * radius * radius;
    }
    @Override
    public String getShapeName() {
        return "Circle";
    }
}