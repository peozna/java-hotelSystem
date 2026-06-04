package nattrn_2;

/**
 * Klassen User representerar en användare i hotellsystemet.
 *
 * En användare har:
 * - ett unikt användarnamn
 * - en roll (t.ex. gäst eller receptionist)
 */

public class User {
    private String username;
    private String role;

    /**
     * Konstruktor skapar en ny användare med angivet användarnamn och roll.
     *
     * @param username användarens unika namn
     * @param role användarens roll i systemet
     */
    public User (String username, String role) {
        this.username = username;
        this.role= role;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }
}
