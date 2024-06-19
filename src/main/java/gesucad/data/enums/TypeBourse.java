package gesucad.data.enums;

public enum TypeBourse {
    AUCUNE, DEMI,COMPLETE;
    public static TypeBourse getValue(String value) {
        for (TypeBourse type : TypeBourse.values()) {
            if (type.name().equalsIgnoreCase(value)) {
                return type;
            }
        }
        return null;
    }

}

