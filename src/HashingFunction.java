import java.io.*;
import java.util.*;

public class HashingFunction {
    public static void main(String[] args) {
        Map<Integer, LinkedList<Integer>> slots = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            slots.put(i, new LinkedList<>());
        }

        String fileName = "numbers.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] numbers = line.split("\\s+");
                for (String num : numbers) {
                    int number = Integer.parseInt(num);
                    int slot = number % 11;
                    LinkedList<Integer> slotList = slots.computeIfAbsent(slot, k -> new LinkedList<>());
                    if (!slotList.search(number)) {
                        slotList.add(number);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number to search: ");
        int number = scanner.nextInt();
        int slot = number % 11;
        LinkedList<Integer> slotList = slots.computeIfAbsent(slot, k -> new LinkedList<>());

        if (slotList.search(number)) {
            slotList.delete(number);
            System.out.println("Number found and removed from slot " + slot);
        } else {
            slotList.add(number);
            System.out.println("Number not found and added to slot " + slot);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 0; i < 10; i++) {
                writer.write("Slot " + i + ": ");
                Node<Integer> current = slots.get(i).head;
                while (current != null) {
                    writer.write(current.data + " ");
                    current = current.next;
                }
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
