package co.napsak.api.model.domain;

public enum Roles {

    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER"),
    PLACE_OWNER("ROLE_OWNER");

    private String roleName;

    Roles(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
