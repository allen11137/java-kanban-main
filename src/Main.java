import model.Epic;
import model.Subtask;
import model.Task;
import service.InMemoryTaskManager;
import service.Managers;
import service.TaskManager;

public class Main {

    public static void main(String[] args) {
        TaskManager manager = new Managers().getDefault();
        Task task1 = new Task("first", "info 1");
        manager.createTask(task1);

        Task task2 = new Task("second", "info 2");
        manager.createTask(task2);

        Epic epic1 = new Epic("epic1", "info epic 1" );
        manager.createTask(epic1);
        Subtask subtask1 = new Subtask(epic1.getId(), "subtask 1", "info subtask 1");
        manager.createTask(subtask1);
        Subtask subtask2 = new Subtask(epic1.getId(), "subtask 2", "info subtask 2");
        manager.createTask(subtask2);
        Epic epic2 = new Epic("epic 2", "info epic 2");
        manager.createTask(epic2);
        Subtask subtask3 = new Subtask(epic2.getId(), "subtask 3", "info subtask 3");
        manager.createTask(subtask3);
//

        manager.getById(3);
        manager.getById(2);
        manager.getById(2);
        manager.getById(2);
        manager.getById(4);
        manager.getById(4);
        manager.getById(5);
        manager.getById(7);
        manager.getById(7);
        manager.getById(3);

//        manager.getHistory().forEach(System.out::println);
//        manager.removeById(4);
//        manager.getHistory().forEach(System.out::println);
        System.out.println("______________________");
//        manager.removeById(epic1.getId());
//        manager.removeById(subtask2.getId());
        manager.removeById(subtask1.getId());
        manager.getHistory().forEach(System.out::println);
/* «алезла в документацию LinkedList и сделала реализацию на ее основе.
“акже исправила баг по пункту из задани€(после каждого запроса выведите историю и убедитесь, что в ней нет повторов).
 */

    }
}
