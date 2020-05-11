package Classes;

/**
 * Role class handles all information associated with a user's role.
 */
public class Role {
    private int id;
    private String name;
    private String description;

    public Role(int id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }

    /**
     * Enum for types of roles a user can be.
     */
    public enum Type {
        ADMIN(1),
        MANAGER(2),
        CUSTOMER(3);

        private int code;

        Type(int code){
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        /**
         * Checks to see if the role type code exists
         * @param code Integer value for role
         * @return Boolean checks if the code exists
         */
        public static boolean codeExists(int code){
            boolean exists = false;
            for(Type type: Type.values()){
                if(type.code == code){
                   exists = true;
                    break;
                }
            }
            return exists;
        }

        /**
         * Gets type of role depending on code
         * @param code Integer value for role
         * @return Type Enum
         */
        public static Type getType(int code){
            Type temp = null;
            for(Type type: Type.values()){
                if(type.code == code){
                    temp = type;
                    break;
                }
            }
            return temp;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
