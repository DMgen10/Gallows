package dev.dmitry;

public class SelectStyle {

    private final InputService service = new InputService();

    public boolean selectStyle() {
        System.out.println("выберите стиль отображения:");
        System.out.println("1 - классическая виселица");
        System.out.println("2 - емодзи");

        while (true) {
            String choice = service.readMenuInput();
            if (choice.equals("1")) {
                return false;
            } else if (choice.equals("2")) {
                return true;
            } else {
                System.out.println("вы вводите что-то другое...");
            }
        }
    }
}
