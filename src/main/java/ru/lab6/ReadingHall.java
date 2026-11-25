package ru.lab6;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class ReadingHall extends JFrame {

    private final Catalog catalog = new Catalog();
    private final DefaultListModel<Book> listModel = new DefaultListModel<>();

    private final JList<Book> bookList = new JList<>(listModel);
    private final JTextField genreField  = new JTextField(14);
    private final JTextField authorField = new JTextField(14);
    private final JTextField titleField  = new JTextField(14);
    private final JTextField yearField   = new JTextField(14);

    private final JButton issueBtn   = new JButton("Выдать");
    private final JButton returnBtn  = new JButton("Вернуть");
    private final JButton refreshBtn = new JButton("Обновить");
    private final JButton addBtn     = new JButton("Добавить");
    private final JButton editBtn    = new JButton("Изменить");
    private final JButton deleteBtn  = new JButton("Удалить");

    private final JLabel statusLabel = new JLabel("Готово. Записей: " + catalog.size());

    public ReadingHall() {
        super("Читальный зал");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel root = new JPanel(new BorderLayout(8, 8));
        root.setBorder(new EmptyBorder(8, 8, 8, 8));
        root.add(toolbar(), BorderLayout.NORTH);
        root.add(center(),  BorderLayout.CENTER);
        root.add(side(),    BorderLayout.EAST);
        root.add(statusLabel, BorderLayout.SOUTH);

        setContentPane(root);
        pack();
        setMinimumSize(getSize());
        setLocationRelativeTo(null);

        fillList();
        listen();
        setButtonsEnabled(false);
    }

    private JPanel toolbar() {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
        p.add(issueBtn);
        p.add(returnBtn);
        p.add(refreshBtn);
        return p;
    }

    private JScrollPane center() {
        JScrollPane sp = new JScrollPane(bookList);
        sp.setPreferredSize(new Dimension(330, 190));
        return sp;
    }

    private JPanel side() {
        JPanel panel = new JPanel(new BorderLayout(8, 8));

        JPanel form = new JPanel(new GridLayout(4, 2, 6, 6));
        form.setBorder(new TitledBorder("Карточка книги"));
        form.add(new JLabel("Жанр:"));     form.add(genreField);
        form.add(new JLabel("Автор:"));    form.add(authorField);
        form.add(new JLabel("Название:")); form.add(titleField);
        form.add(new JLabel("Год:"));      form.add(yearField);

        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
        buttons.add(addBtn);
        buttons.add(Box.createVerticalStrut(6));
        buttons.add(editBtn);
        buttons.add(Box.createVerticalStrut(6));
        buttons.add(deleteBtn);

        panel.add(form,    BorderLayout.NORTH);
        panel.add(buttons, BorderLayout.CENTER);
        return panel;
    }

    private void fillList() {
        listModel.clear();
        for (int i = 0; i < catalog.size(); i++) {
            listModel.addElement(catalog.get(i));
        }
    }

    private void setButtonsEnabled(boolean enabled) {
        issueBtn.setEnabled(enabled);
        returnBtn.setEnabled(enabled);
        editBtn.setEnabled(enabled);
        deleteBtn.setEnabled(enabled);
    }

    private void listen() {
        addBtn.addActionListener(e -> {
            Catalog.Result r = catalog.add(
                    genreField.getText(), authorField.getText(),
                    titleField.getText(), yearField.getText());
            afterChange(r);
        });

        editBtn.addActionListener(e -> {
            Catalog.Result r = catalog.update(bookList.getSelectedIndex(),
                    genreField.getText(), authorField.getText(),
                    titleField.getText(), yearField.getText());
            afterChange(r);
        });

        deleteBtn.addActionListener(e -> {
            Catalog.Result r = catalog.delete(bookList.getSelectedIndex());
            afterChange(r);
        });

        issueBtn.addActionListener(e -> {
            Catalog.Result r = catalog.markIssued(bookList.getSelectedIndex(), true);
            afterChange(r);
        });

        returnBtn.addActionListener(e -> {
            Catalog.Result r = catalog.markIssued(bookList.getSelectedIndex(), false);
            afterChange(r);
        });

        refreshBtn.addActionListener(e -> {
            fillList();
            statusLabel.setText("Готово. Записей: " + catalog.size());
        });

        bookList.addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) return;
            Book b = bookList.getSelectedValue();
            boolean has = (b != null);
            setButtonsEnabled(has);
            if (has) {
                genreField.setText(b.getGenre());
                authorField.setText(b.getAuthor());
                titleField.setText(b.getTitle());
                yearField.setText(String.valueOf(b.getYear()));
            }
        });
    }

    private void afterChange(Catalog.Result r) {
        statusLabel.setText(r.message());
        if (r.ok()) {
            fillList();
            if (catalog.size() == 0) setButtonsEnabled(false);
        }
    }
}