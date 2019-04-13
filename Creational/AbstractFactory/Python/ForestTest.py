import Forest

conifer = Forest.ConiferForestFactory()
deciduous = Forest.DeciduousForestFactory()
tropical = Forest.TropicalForestFactory()

def createForest(factory, treesCount):
    for i in range(treesCount):
        tree = factory.createRandomTree()
        tree.spawn()

print("Conifer forest:")
createForest(conifer, 10)

print("\nDeciduous forest:")
createForest(deciduous, 10)

print("\nTropical forest:")
createForest(tropical, 10)
