class Sphere
{
    constructor(radius)
    {
        this.radius = radius;
    }
    
    render(renderer)
    {
        renderer.renderSphere(this);
    }
}

class Box
{
    constructor(size)
    {
        this.size = size;
    }
    
    render(renderer)
    {
        renderer.renderBox(this);
    }
}

class ScanlineRenderer
{
    renderSphere(s)
    {
        console.log("Rendering a sphere with radius ".concat(s.radius.toString()).concat(" by scanline method."));
    }
    
    renderBox(b)
    {
        console.log("Rendering a box with size ".concat(b.size.toString()).concat(" by scanline method."));
    }
}

class RaytracingRenderer
{
    renderSphere(s)
    {
        console.log("Rendering a sphere with radius ".concat(s.radius.toString()).concat(" by raytracing method."));
    }
    
    renderBox(b)
    {
        console.log("Rendering a box with size ".concat(b.size.toString()).concat(" by raytracing method."));
    }
}

// Test

var sphere = new Sphere(3);
var box = new Box(10);
var scanline = new ScanlineRenderer();
var raytracer = new RaytracingRenderer();

sphere.render(scanline);
sphere.render(raytracer);
box.render(scanline);
box.render(raytracer);