public class TestQuake {
  public static void main(String[] args) {
    EarthQuakeClient cliente = new EarthQuakeClient();
    //cliente.createCSV();
    //cliente.bigQuakes();
    //cliente.closeToMe();
    cliente.quakesOfPhrase();
    //cliente.findCloseQuakes();
    //cliente.quakesOfDepth();
  }
}
