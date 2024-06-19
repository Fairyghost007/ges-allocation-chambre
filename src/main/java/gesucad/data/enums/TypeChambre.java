package gesucad.data.enums;

public enum TypeChambre {
    Individulle,Double;

    public  static TypeChambre getValue(String value){
        for (TypeChambre tC: TypeChambre.values()) {
             if (tC.name().compareToIgnoreCase(value)==0) {
                   return tC; 
             }
        }
        return null;
     }
}
