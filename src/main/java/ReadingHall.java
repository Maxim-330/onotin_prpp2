import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

/*
 Лабораторная работа № 5. Вариант 1 — «Читальный зал».

 Обоснование выбора менеджеров компоновки:
  — Корень — BorderLayout: окно делится на 4 зоны (север/центр/восток/юг).
  — Панель инструментов — FlowLayout: три кнопки в ряд слева.
  — Форма — GridLayout(4,2): строгая сетка «подпись — поле».
  — Колонка кнопок — BoxLayout(Y): кнопки друг под другом с распорками.
 */
public class ReadingHall {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            System.out.println("Onotin Maxim, группа 3825Б1ПРпп2");
            JFrame frame = build();
            printTree(frame.getContentPane(), 0);
            frame.setVisible(true);
        });
    }

    // готовое окно
    public static JFrame build() {
        JFrame frame = new JFrame("Читальный зал");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel root = new JPanel(new BorderLayout(8, 8));
        root.setBorder(new EmptyBorder(8, 8, 8, 8));
        root.add(toolbar(), BorderLayout.NORTH);
        root.add(center(),  BorderLayout.CENTER);
        root.add(side(),    BorderLayout.EAST);
        root.add(status(),  BorderLayout.SOUTH);

        frame.setContentPane(root);
        frame.pack();
        frame.setMinimumSize(frame.getSize());
        frame.setLocationRelativeTo(null);
        return frame;
    }

    // север через три кнопки в ряд
    private static JPanel toolbar() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
        p.add(new JButton("Выдать"));
        p.add(new JButton("Вернуть"));
        p.add(new JButton("Обновить"));
        return p;
    }

    private static JScrollPane center() {
        String[] books = {
                "Лем — Солярис",
                "Булгаков — Мастер и Маргарита",
                "Стругацкие — Пикник на обочине",
                "Кристи — Убийство в Восточном экспрессе",
                "Толстой — Анна Каренина",
                "Конан Дойл — Собака Баскервилей",
                "Брэдбери — 451 градус по Фаренгейту",
                "Кристи — Загадка Эндхауза"
        };

        JScrollPane sp = new JScrollPane(new JList<>(books));
        sp.setPreferredSize(new Dimension(330, 190));
        return sp;

    }

    // форма сверху кнопки снизу
    private static JPanel side() {
        JPanel panel = new JPanel(new BorderLayout(8, 8));

        JPanel form = new JPanel(new GridLayout(4, 2, 6, 6));
        form.setBorder(new TitledBorder("Карточка книги"));
        form.add(new JLabel("Жанр:"));     form.add(new JTextField(14));
        form.add(new JLabel("Автор:"));    form.add(new JTextField(14));
        form.add(new JLabel("Название:")); form.add(new JTextField(14));
        form.add(new JLabel("Год:"));      form.add(new JTextField(14));

        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
        buttons.add(new JButton("Добавить"));
        buttons.add(Box.createVerticalStrut(6));
        buttons.add(new JButton("Изменить"));
        buttons.add(Box.createVerticalStrut(6));
        buttons.add(new JButton("Удалить"));

        panel.add(form,    BorderLayout.NORTH);
        panel.add(buttons, BorderLayout.CENTER);
        return panel;
    }

    private static JLabel status() {
        return new JLabel("Готово. Записей: 8");
    }

    // печать дерева компонентов
    public static void printTree(Component c, int depth) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < depth; i++) sb.append("    ");
        sb.append(c.getClass().getSimpleName());

        if (c instanceof JPanel && ((JPanel) c).getLayout() != null) {
            sb.append(" [").append(((JPanel) c).getLayout().getClass().getSimpleName()).append("]");
        }
        System.out.println(sb);

        if (c instanceof JScrollPane) {
            printTree(((JScrollPane) c).getViewport().getView(), depth + 1);
        }
        else if (c instanceof Container) {
            for (Component child : ((Container) c).getComponents()) {
                if (child instanceof Box.Filler) {
                    StringBuilder f = new StringBuilder();
                    for (int i = 0; i < depth + 1; i++) f.append("    ");
                    f.append("Filler");
                    System.out.println(f);
                }
                else {
                    printTree(child, depth + 1);
                }
            }
        }
    }
}