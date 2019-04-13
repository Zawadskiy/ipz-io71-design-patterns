
import java.util.Random;
import java.util.Arrays;
import java.util.List;

interface Tree
{
    void spawn();
}

class Pine implements Tree {
    @Override
    public void spawn()
    {
        System.out.println("Pine");
    }
}

class Spruce implements Tree {
    @Override
    public void spawn()
    {
        System.out.println("Spruce");
    }
}

class Elm implements Tree {
    @Override
    public void spawn()
    {
        System.out.println("Elm");
    }
}

class Oak implements Tree {
    @Override
    public void spawn()
    {
        System.out.println("Oak");
    }
}

class Palm implements Tree {
    @Override
    public void spawn()
    {
        System.out.println("Palm");
    }
}

class Fern implements Tree {
    @Override
    public void spawn()
    {
        System.out.println("Fern");
    }
}

@FunctionalInterface
interface Instantiator<T>
{
    public T create();
}

class ForestFactory
{
    protected List<Instantiator<Tree>> TreeTypes;
    
    @SafeVarargs
    public ForestFactory(Instantiator<Tree>...treeTypes)
    {
        TreeTypes = Arrays.asList(treeTypes);
    }
    
    public Tree createRandomTree()
    {
        int treeTypeId = new Random().nextInt(TreeTypes.size());
        return TreeTypes.get(treeTypeId).create();
    }
}


public class Forest
{
    private static void createForest(ForestFactory factory, int treesCount)
    {
        for (int i = 0; i < treesCount; i++)
        {
            Tree t = factory.createRandomTree();
            t.spawn();
        }
    }
    
    public static void main(String[] args)
    {
        ForestFactory conifer = new ForestFactory(() -> new Pine(), () -> new Spruce());
        ForestFactory deciduous = new ForestFactory(() -> new Elm(), () -> new Oak());
        ForestFactory tropical = new ForestFactory(() -> new Palm(), () -> new Fern());
        
        System.out.println("Conifer forest:");
        createForest(conifer, 10);
        
        System.out.println("\nDeciduous forest:");
        createForest(deciduous, 10);
        
        System.out.println("\nTropical forest:");
        createForest(tropical, 10);
    }

}
