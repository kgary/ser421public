import java.util.Date;
import java.util.Map;
import java.util.HashMap;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingListener;

/**
Example taken from http://www.java2s.com/Code/Java/Servlets
*/
public class SessionListen implements HttpSessionListener, HttpSessionAttributeListener, HttpSessionBindingListener {

  private int sessionCount;
    private Map<String,String> info;

  public SessionListen() {
    this.sessionCount = 0;
    info = new HashMap<String,String>();
  }

  public void sessionCreated(HttpSessionEvent se) {
    HttpSession session = se.getSession();
    session.setMaxInactiveInterval(60);
    synchronized (this) {
      sessionCount++;
    }
    String id = session.getId();
    Date now = new Date();
    String message = new StringBuffer("New Session created on ").append(
        now.toString()).append("\nID: ").append(id).append("\n")
        .append("There are now ").append("" + sessionCount).append(
            " live sessions in the application.").toString();

    System.out.println(message);
  }

  public void sessionDestroyed(HttpSessionEvent se) {

    HttpSession session = se.getSession();
    String id = session.getId();
    synchronized (this) {
      --sessionCount;
    }
    String message = new StringBuffer("Session destroyed"
        + "\nValue of destroyed session ID is").append("" + id).append(
        "\n").append("There are now ").append("" + sessionCount)
        .append(" live sessions in the application.").toString();
    System.out.println(message);
  }

    // Methods for HttpSessionAttributeListener
  public void attributeAdded(HttpSessionBindingEvent se) {

    HttpSession session = se.getSession();
    String id = session.getId();
    String name = se.getName();
    String value = (String) se.getValue();
    String source = se.getSource().getClass().getName();
    String message = new StringBuffer("Attribute bound to session in ")
        .append(source).append("\nThe attribute name: ").append(name)
        .append("\n").append("The attribute value:").append(value)
        .append("\n").append("The session ID: ").append(id).toString();
    System.out.println(message);
  }

  public void attributeRemoved(HttpSessionBindingEvent se) {

    HttpSession session = se.getSession();
    String id = session.getId();
    String name = se.getName();
    if (name == null)
      name = "Unknown";
    String value = (String) se.getValue();
    String source = se.getSource().getClass().getName();
    String message = new StringBuffer("Attribute unbound from session in ")
        .append(source).append("\nThe attribute name: ").append(name)
        .append("\n").append("The attribute value: ").append(value)
        .append("\n").append("The session ID: ").append(id).toString();
    System.out.println(message);
  }

  public void attributeReplaced(HttpSessionBindingEvent se) {

    String source = se.getSource().getClass().getName();
    String message = new StringBuffer("Attribute replaced in session  ")
        .append(source).toString();
    System.out.println(message);
  }

  // methods for HttpSessionBindListener
  public void valueBound(HttpSessionBindingEvent be) {

    HttpSession session = be.getSession();
    String id = session.getId();
    String name = be.getName();
    Object value = be.getValue();
    String source = be.getSource().getClass().getName();
    String message = new StringBuffer("Attribute bound to session in ")
        .append(source).append("\nThe attribute name: ").append(name)
        .append("\n").append("The attribute value: ").append(value)
        .append("\n").append("The session id: ").append(id).toString();

    System.out.println(message);
  }

  public void valueUnbound(HttpSessionBindingEvent be) {

    HttpSession session = be.getSession();
    String id = session.getId();
    String name = be.getName();
    if (name == null)
      name = "Unknown";
    String source = be.getSource().getClass().getName();
    String message = new StringBuffer("Attribute unbound from session in ")
        .append(source).append("\nThe attribute name: ").append(name)
        .append("\n").append("The session id: ").append(id).toString();
    //clear Map; send message
    info.clear();
    System.out.println(message + "\nThe size of the HashMap is: "
        + info.size());
  }

  public void addInfo(String name, String email) {

    info.put(email, name);

  }
}