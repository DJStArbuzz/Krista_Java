public class Main {
  public static void main(String[] args) {
    Box box = new Box();
    Box box2 = new Box();
    Box box3 = new Box(10, 5, 4);
    box.setDepth(30);
    box.setHeight(20);
    box.setWidth(-10);

    double value = box.getDepth() * box.getWidth() * box.getHeight();
    System.out.println(value);
    System.out.println(box2.value());
    System.out.println(box3.value());

    Test test = new Test();
    test.test();
    test.test(1);
    test.test(1.0);
    test.test(2, 3);
  }
}

public class Box {
    private double width;
    private double height;
    private double depth;

    // Конструктор и его перегрузка
    Box() {
        this.width = 10;
        this.height = 10;
        this.depth = 10;
    } 
    Box(double width, double height, double depth){
        if (width < 0){
            width *= -1;
        }
        if (height < 0){
            height *= -1;
        }
        if (depth < 0){
            depth *= -1;
        }
        this.width = width;
        this.height = height;
        this.depth = depth;
    }

    public void setWidth(double  width){
        if (width < 0){
            width *= -1;
        }
        this.width = width;
    }

    public void setHeight(double  height){
        if (height < 0){
            height *= -1;
        }
        this.height = height;
    }

    public void setDepth(double  depth){
        if (depth < 0){
            depth *= -1;
        }
        this.depth = depth;
    }

    public double getWidth(){
        return width;
    }

    public double getHeight(){
        return height;
    }

    public double getDepth(){
        return depth;
    }

    public double value(){
        return height * depth * width;
    }
}

class Test{
    public void test(){
        System.out.println("Аргументов не дали."); 
   }

   public void test(int a){
        System.out.println("1 аргумент дали типа int."); 
   }
    
    public void test(double a){
        System.out.println("1 аргумент дали типа double."); 
   }

   
   public void test(int a, int b){
        System.out.println("2 аргумента дали типа int."); 
   }
    
}
