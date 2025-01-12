package com.example.oneinone_alltoolsapp.MaathsandFinance;

public class VolumeCalculator {

    // Cube
    public static double calculateCubeVolume(double side) {
        return Math.pow(side, 3);
    }

    // Rectangular Prism
    public static double calculateRectangularPrismVolume(double length, double width, double height) {
        return length * width * height;
    }

    // Sphere
    public static double calculateSphereVolume(double radius) {
        return (4.0 / 3) * Math.PI * Math.pow(radius, 3);
    }

    // Cylinder
    public static double calculateCylinderVolume(double radius, double height) {
        return Math.PI * Math.pow(radius, 2) * height;
    }

    // Cone
    public static double calculateConeVolume(double radius, double height) {
        return (1.0 / 3) * Math.PI * Math.pow(radius, 2) * height;
    }

    // Pyramid
    public static double calculatePyramidVolume(double baseArea, double height) {
        return (1.0 / 3) * baseArea * height;
    }
}
