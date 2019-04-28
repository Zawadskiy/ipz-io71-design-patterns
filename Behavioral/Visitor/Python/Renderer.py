import abc

class AbstractRenderer(metaclass = abc.ABCMeta):
    @abc.abstractmethod
    def renderSphere(self, sphere):
        return
    @abc.abstractmethod
    def renderBox(self, box):
        return

class SceneObject:
    def __init__(self):
        self.name = "Undefined"

    def render(self, renderer):
        print("{0} cannot be rendered with {1}".format(self.name, renderer))

class Sphere(SceneObject):
    def __init__(self, radius):
        self.name = "Sphere"
        self.radius = radius
     
    def render(self, renderer):
        renderer.renderSphere(self)
        
class Box(SceneObject):
    def __init__(self, size):
        self.name = "Box"
        self.size = size
     
    def render(self, renderer):
        renderer.renderBox(self)
        
class ScanlineRenderer(AbstractRenderer):
    def renderSphere(self, sphere):
        print("Rendering a sphere with radius {0} by scanline method.".format(sphere.radius))
        
    def renderBox(self, box):
        print("Rendering a box with size {0} by scanline method.".format(box.size))
        
class RaytracingRenderer(AbstractRenderer):
    def renderSphere(self, sphere):
        print("Rendering a sphere with radius {0} by raytracing method.".format(sphere.radius))
        
    def renderBox(self, box):
        print("Rendering a box with size {0} by raytracing method.".format(box.size))
        
        
sphere = Sphere(3)
box = Box(10)
scanline = ScanlineRenderer()
raytracer = RaytracingRenderer()

sphere.render(scanline)
sphere.render(raytracer)
box.render(scanline)
box.render(raytracer)