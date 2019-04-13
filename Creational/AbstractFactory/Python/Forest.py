import abc
import random

class Tree(metaclass = abc.ABCMeta):
    @abc.abstractmethod
    def spawn(self):
        return

class Pine(Tree):
    def spawn(self):
        print("Pine")

class Spruce(Tree):
    def spawn(self):
        print("Spruce")

class Elm(Tree):
    def spawn(self):
        print("Elm")

class Oak(Tree):
    def spawn(self):
        print("Oak")
        
class Palm(Tree):
    def spawn(self):
        print("Palm")
        
class Fern(Tree):
    def spawn(self):
        print("Fern")

class ForestFactory(metaclass = abc.ABCMeta):
    TreeTypes = []
    
    def createRandomTree(self):
        return self.TreeTypes[random.randrange(len(self.TreeTypes))]()

class ConiferForestFactory(ForestFactory):
    TreeTypes = [Pine, Spruce]

class DeciduousForestFactory(ForestFactory):
    TreeTypes = [Elm, Oak]
    
class TropicalForestFactory(ForestFactory):
    TreeTypes = [Palm, Fern]
