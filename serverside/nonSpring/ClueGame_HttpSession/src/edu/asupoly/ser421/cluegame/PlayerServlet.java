package edu.asupoly.ser421.cluegame;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SuppressWarnings("serial")
public class PlayerServlet extends HttpServlet {
    String[] suspects = { "Miss Scarlet", "Professor Plum", "Mrs. Peacock", "Reverend Green", "Colonel Mustard",
            "Mrs. White" };
    String[] rooms = { "Kitchen", "Ballroom", "Conservatory", "Dining room", "Lounge", "Hall", "Study", "Library",
            "Billiard Room" };
    String[] weapons = { "Candlestick", "Dagger", "Lead pipe", "Revolver", "Rope", "Spanner" };

    List<String> playerRooms;
    List<String> playerSuspects;
    List<String> playerWeapons;

    List<String> computerRooms;
    List<String> computerSuspects;
    List<String> computerWeapons;

    String[] winningSecret = new String[3];

    // Use doGet when game is ongoing. Redirect user to this servlet by a GET
    // request after the user has took turn to
    // guess
    @SuppressWarnings("unchecked")
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        out.print("<html><body>");
        out.print("<span>");
        for (String s : suspects)
            out.print(s + ",");
        out.print("</span>");
        out.print("</br>");
        out.print("<span>");
        for (String s : rooms)
            out.print(s + ",");
        out.print("</span>");
        out.print("</br>");
        out.print("<span>");
        for (String s : weapons)
            out.print(s + ",");
        out.print("</span>");
        out.print("</br>");
        out.print("</br>");

        out.print("<p>Your Guess " + (String) session.getAttribute("playerName") + ":</p>");

        // Form for playing the game with select widgets
        out.print("<form method=\"POST\" action=\"guess\">");
        out.print("<select id=\"suspect\" name=\"suspect\">");
        playerRooms = (ArrayList<String>) session.getAttribute("playerRoomsList");
        playerSuspects = (ArrayList<String>) session.getAttribute("playerSuspectsList");
        playerWeapons = (ArrayList<String>) session.getAttribute("playerWeaponsList");
        for (String s : playerSuspects) {
            out.print("<option value=\"" + s + "\">" + s + "</option>");
        }
        out.print("</select>");
        out.print("</br>");
        out.print("<select id=\"room\" name=\"room\">");
        for (String s : playerRooms) {
            out.print("<option value=\"" + s + "\">" + s + "</option>");
        }
        out.print("</select>");
        out.print("</br>");
        out.print("<select id=\"weapon\" name=\"weapon\">");
        for (String s : playerWeapons) {
            out.print("<option value=\"" + s + "\">" + s + "</option>");
        }
        out.print("</select>");
        out.print("</br>");
        out.print("<input type=\"submit\" value=\"Submit\"/>");
        out.print("</form>");

        out.close();
    }

    // Use doPost to start a new game. When user enters name, submit the form to
    // post and create new session
    @SuppressWarnings("unchecked")
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Creates a new session if one does not exist
        HttpSession session = req.getSession(true);
        session.setAttribute("playerName", req.getParameter("playerName"));

        // If a new session, generate player's cards and computer's cards and add them
        // to the current session
        // Additionally, randomly generate a winning secret and add that to the current
        // session
        if (session.isNew()) {
            playerRooms = new ArrayList<>();
            playerSuspects = new ArrayList<>();
            playerWeapons = new ArrayList<>();

            computerRooms = new ArrayList<>();
            computerSuspects = new ArrayList<>();
            computerWeapons = new ArrayList<>();

            Random r = new Random();
            Guess winningSecret = new Guess(suspects[r.nextInt(suspects.length)],
                    weapons[r.nextInt(weapons.length)],
                    rooms[r.nextInt(rooms.length)]);

            for (int i = 0; i < suspects.length; i++) {
                if (winningSecret.suspect.equals(suspects[i])) {
                    playerSuspects.add(suspects[i]);
                    computerSuspects.add(suspects[i]);
                } else if (i % 2 == 0)
                    playerSuspects.add(suspects[i]);
                else
                    computerSuspects.add(suspects[i]);
            }
            for (int i = 0; i < rooms.length; i++) {
                if (winningSecret.room.equals(rooms[i])) {
                    playerRooms.add(rooms[i]);
                    computerRooms.add(rooms[i]);
                } else if (i % 2 == 0)
                    playerRooms.add(rooms[i]);
                else
                    computerRooms.add(rooms[i]);
            }
            for (int i = 0; i < weapons.length; i++) {
                if (winningSecret.weapon.equals(weapons[i])) {
                    playerWeapons.add(weapons[i]);
                    computerWeapons.add(weapons[i]);
                } else if (i % 2 == 0)
                    playerWeapons.add(weapons[i]);
                else
                    playerWeapons.add(weapons[i]);
            }

            System.out.println("Winning Secret " + winningSecret);
            session.setAttribute("playerRoomsList", playerRooms);
            session.setAttribute("computerRoomsList", computerRooms);
            session.setAttribute("playerSuspectsList", playerSuspects);
            session.setAttribute("computerSuspectsList", computerSuspects);
            session.setAttribute("playerWeaponsList", playerWeapons);
            session.setAttribute("computerWeaponsList", computerWeapons);
            session.setAttribute("winningSecret", winningSecret);
        }

        PrintWriter out = resp.getWriter();

        out.print("<html><body>");
        out.print("<span>");
        for (String s : suspects)
            out.print(s + ",");
        out.print("</span>");
        out.print("</br>");
        out.print("<span>");
        for (String s : rooms)
            out.print(s + ",");
        out.print("</span>");
        out.print("</br>");
        out.print("<span>");
        for (String s : weapons)
            out.print(s + ",");
        out.print("</span>");
        out.print("</br>");
        out.print("</br>");

        out.print("<p>Your Guess " + (String) session.getAttribute("playerName") + ":</p>");

        out.print("<form method=\"POST\" action=\"guess\">");
        out.print("<select id=\"suspect\" name=\"suspect\">");
        playerRooms = (ArrayList<String>) session.getAttribute("playerRoomsList");
        playerSuspects = (ArrayList<String>) session.getAttribute("playerSuspectsList");
        playerWeapons = (ArrayList<String>) session.getAttribute("playerWeaponsList");
        for (String s : playerSuspects) {
            out.print("<option value=\"" + s + "\">" + s + "</option>");
        }
        out.print("</select>");
        out.print("</br>");
        out.print("<select id=\"room\" name=\"room\">");
        for (String s : playerRooms) {
            out.print("<option value=\"" + s + "\">" + s + "</option>");
        }
        out.print("</select>");
        out.print("</br>");
        out.print("<select id=\"weapon\" name=\"weapon\">");
        for (String s : playerWeapons) {
            out.print("<option value=\"" + s + "\">" + s + "</option>");
        }
        out.print("</select>");
        out.print("</br>");
        out.print("<input type=\"submit\" value=\"Submit\"/>");
        out.print("</form>");

        out.close();

    }
}
