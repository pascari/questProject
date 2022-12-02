import com.quest.GameServlet;
import com.quest.questions.Answer;
import com.quest.questions.Question;
import com.quest.questions.QuestionManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ServletTest {

    HttpServletRequest request = mock(HttpServletRequest.class);

    HttpServletResponse response = mock(HttpServletResponse.class);

    HttpSession session = mock(HttpSession.class);

    Question question1 = new Question(1, "You have lost your memory. Take the UFO Challenge?",List.of(
            new Answer("Accept the Challenge"),
                new Answer("Reject the Challenge", "You rejected the Challenge. You Defeat!")));

    Question question2 = new Question(2, "You have accepted the Challenge. Going up to the bridge to see the captain?", List.of(
            new Answer("Accept to climb the bridge"),
            new Answer("Refuse to climb the bridge", "You didn't going to negotiate. You Defeat!")));

    @Test
    @DisplayName("Checking for 'Bad Answer' and ending the quest")
    public void doGetGameServletTestWrongAnswer() throws IOException {
        GameServlet gameServlet = new GameServlet();
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("answerId")).thenReturn("1");
        doNothing().when(session).setAttribute(anyString(), any());
        when(session.getAttribute("question")).thenReturn(question1);
        QuestionManager questionManager = spy(new QuestionManager());
        when(questionManager.getById(2)).thenReturn(question2);
        gameServlet.doGet(request, response);
        verify(session).setAttribute(eq("wrongAnswer"), any(Answer.class));
    }

    @Test
    @DisplayName("Checking for 'Good Answer' and continuing the quest")
    public void doGetGameServletTestRightAnswer() throws IOException {
        GameServlet gameServlet = new GameServlet();
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("answerId")).thenReturn("0");
        doNothing().when(session).setAttribute(anyString(), any());
        when(session.getAttribute("question")).thenReturn(question1);
        QuestionManager questionManager = spy(new QuestionManager());
        when(questionManager.getById(2)).thenReturn(question2);
        gameServlet.doGet(request, response);
        verify(session).setAttribute(eq("question"), any(Question.class));
        verify(response).sendRedirect("/game.jsp");
    }
}
