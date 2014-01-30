package controller;

import javax.servlet.http.HttpServletRequest;

import model.Model;

/*
 * Processes the parameters from the form in login.jsp.
 * If successful, set the "user" session attribute to the
 * user's User bean and then redirects to view the originally
 * requested photo.  If there was no photo originally requested
 * to be viewed (as specified by the "redirect" hidden form
 * value), just redirect to manage.do to allow the user to manage
 * his photos.
 */
public class EntryAction extends Action {

        public EntryAction(Model model) {
        }

        public String getName() {
                return "entry.do";
        }

        public String perform(HttpServletRequest request) {
                return "entry.jsp";
        }
}