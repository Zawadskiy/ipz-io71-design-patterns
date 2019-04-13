function Pine()
{
    this.spawn = function()
    {
        console.log("Pine");
    };
}

function Spruce()
{
    this.spawn = function()
    {
        console.log("Spruce");
    };
}

function Elm()
{
    this.spawn = function()
    {
        console.log("Elm");
    };
}

function Oak()
{
    this.spawn = function()
    {
        console.log("Oak");
    };
}

function Palm()
{
    this.spawn = function()
    {
        console.log("Palm");
    };
}

function Fern()
{
    this.spawn = function()
    {
        console.log("Fern");
    };
}

function ForestFactory()
{
    this.TreeTypes = [];
    this.createRandomTree = function()
    {
        if (this.TreeTypes.length > 0)
            return new this.TreeTypes[Math.floor(Math.random() * this.TreeTypes.length)]();
        else
            return null;
    };
}

function ConiferForestFactory()
{
    ForestFactory.call(this);
    this.TreeTypes = [Pine, Spruce];
}

function DeciduousForestFactory()
{
    ForestFactory.call(this);
    this.TreeTypes = [Elm, Oak];
}

function TropicalForestFactory()
{
    ForestFactory.call(this);
    this.TreeTypes = [Palm, Fern];
}

// Test

var conifer = new ConiferForestFactory();
var deciduous = new DeciduousForestFactory();
var tropical = new TropicalForestFactory();

function createForest(factory, treesCount)
{
    for (var i = 0; i < treesCount; i++)
    {
        var tree = factory.createRandomTree();
        tree.spawn();
    }
}

console.log("Conifer forest:");
createForest(conifer, 10);
console.log("\nDeciduous forest:");
createForest(deciduous, 10);
console.log("\nTropical forest:");
createForest(tropical, 10);