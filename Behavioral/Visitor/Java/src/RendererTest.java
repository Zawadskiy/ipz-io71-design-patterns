interface AbstractRenderer
{
    void render(Sphere s);
    void render(Box b);
}

interface SceneObject
{
    void render(AbstractRenderer renderer);
}

class Sphere implements SceneObject
{
    private float radius;
    
    public Sphere(float radius)
    {
        this.radius = radius;
    }
    
    public float getRadius()
    {
        return radius;
    }
    
    @Override
    public void render(AbstractRenderer renderer)
    {
        renderer.render(this);
    }
}

class Box implements SceneObject
{
    private float size;
    
    public Box(float size)
    {
        this.size = size;
    }
    
    public float getSize()
    {
        return size;
    }
    
    @Override
    public void render(AbstractRenderer renderer)
    {
        renderer.render(this);
    }
}

class ScanlineRenderer implements AbstractRenderer
{
    public void render(Sphere s)
    {
        System.out.println(String.format("Rendering a sphere with radius %.2f by scanline method.", s.getRadius()));
    }
    
    public void render(Box b)
    {
        System.out.println(String.format("Rendering a box with size %.2f by scanline method.", b.getSize()));
    }
}

class RaytracingRenderer implements AbstractRenderer
{
    public void render(Sphere s)
    {
        System.out.println(String.format("Rendering a sphere with radius %.2f by raytracing method.", s.getRadius()));
    }
    
    public void render(Box b)
    {
        System.out.println(String.format("Rendering a box with size %.2f by raytracing method.", b.getSize()));
    }
}

public class RendererTest
{

    public static void main(String[] args)
    {
        Sphere sphere = new Sphere(3f);
        Box box = new Box(10f);     
        ScanlineRenderer scanline = new ScanlineRenderer();
        RaytracingRenderer raytracer = new RaytracingRenderer();
        
        sphere.render(scanline);
        sphere.render(raytracer);
        box.render(scanline);
        box.render(raytracer);
    }

}
