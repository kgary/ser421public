package edu.asupoly.ser421.cluegame;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.LinkedList;
import java.util.Random;

@SuppressWarnings({ "unchecked", "serial" })
public class GuessServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	  	resp.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    	  	resp.getWriter().println("<html><body><p>Use " + "POST instead!</p></body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html");
        out.print("<html><body>");
        if(session == null){
            System.out.println("Null session found in GuessServlet doPost");
            resp.sendRedirect(resp.encodeRedirectURL("/"));
            return;
        }

        // Create a new guess object which would represent the current guess the player has made
        Guess playerGuess = new Guess(req.getParameter("suspect"),
                req.getParameter("weapon"),
                req.getParameter("room"));


        Guess winningSecret = (Guess) session.getAttribute("winningSecret");
        System.out.println("Winning Secret " + winningSecret);

        // Linked List to maintain the history of guesses in order
		LinkedList<Guess> guessHistory = (LinkedList<Guess>) session.getAttribute("guessHistory");
        if(guessHistory == null)
            guessHistory = new LinkedList<Guess>();

        // Compare the current player guess with guesses in the history to check for a duplicate guess
        if(guessHistory.contains(playerGuess)){
            out.println("<p style=\"color:red\">" + session.getAttribute("playerName") + " you've already made the " + playerGuess + ". Please try again</p>");
            out.print("<form method=\"GET\" action=\"player-servlet\">");
            out.print("<input type=\"Submit\" value=\"Continue\"/>");
            out.print("</form>");
            return;
        }

        // If not duplicate, add the current guess to the history
        guessHistory.add(playerGuess);
        session.setAttribute("guessHistory", guessHistory);


        // Check if the current guess is the winning guess
        if(playerGuess.equals(winningSecret)) {
            out.println("<p style=\"color:red\">" + session.getAttribute("playerName") + " your " + playerGuess + " was correct. You win!</p>");
            session.invalidate();
            out.print("<form method=\"GET\" action=\"./\">");
            out.print("<input type=\"Submit\" value=\"Continue\"/>");
            out.print("</form>");
        }

        // Otherwise respond that the guess and incorrect and display one component of the guess which is incorrect
        else {
            out.println("<p style=\"color:red\">" + session.getAttribute("playerName") + " your " + playerGuess + " was incorrect. You guessed "
                    + playerGuess.whichIsWrong(winningSecret)
                    + " incorrectly. Please try again</p>");
            List<String> computerRooms = (List<String>) session.getAttribute("computerRoomsList");
            List<String> computerSuspects = (List<String>) session.getAttribute("computerSuspectsList");
            List<String> computerWeapons = (List<String>) session.getAttribute("computerWeaponsList");

            // Randomly generate a computer guess and keep generating until the guess is unique by checking
            // against history
            Guess computerGuess = new Guess();
            Random r = new Random();
            do {
                computerGuess.room = computerRooms.get(r.nextInt(computerRooms.size()));
                computerGuess.weapon = computerWeapons.get(r.nextInt(computerWeapons.size()));
                computerGuess.suspect = computerSuspects.get(r.nextInt(computerSuspects.size()));

            }while (guessHistory.contains(computerGuess));
            guessHistory.add(computerGuess);
            session.setAttribute("guessHistory", guessHistory);
            out.print("Computer's " + computerGuess);
            out.print("</br>");

            // If computer's guess is correct. Responds back with a message and ends the game
            if(computerGuess.equals(winningSecret)){
                out.println("<p style=\"color:red\">Computer guess is correct. Computer wins!</p>");
                session.invalidate();
                out.print("<form method=\"GET\" action=\"./\">");
                out.print("<input type=\"Submit\" value=\"Continue\"/>");
                out.print("</form>");
            }

            // Otherwise respond by telling the user the computer guess was incorrect and display one incorrect
            // component of the guess
            else{
                out.println("<p style=\"color:red\">Computer guess is incorrect. Computer guessed "
                        + computerGuess.whichIsWrong(winningSecret) + " incorrectly</p>");
                out.print("<form method=\"GET\" action=\"player-servlet\">");
                out.print("<input type=\"Submit\" value=\"Continue\"/>");
                out.print("</form>");
            }

        }
    }
}
