package lotto;

import camp.nextstep.edu.missionutils.Console;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LottoInput {

    public Integer InputAmount() {
        while (true) {
            System.out.println("구입금액을 입력해 주세요.");
            try {
                String input = Console.readLine();
                int amount = Integer.parseInt(input);
                validateAmount(amount);
                return amount / 1000;
            }
            catch (NumberFormatException e) {
                System.out.println("[ERROR] 구입금액에 문자는 입력할 수 없습니다. 1,000원 단위의 금액을 입력해주세요.");
            }
            catch (IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public List<Integer> InputWinNumber() {
        while (true) {
            System.out.println("당첨 번호를 입력해 주세요.");
            try {
                String[] input = Console.readLine().trim().split("\\s*,\\s*");
                List<Integer> numbers = Arrays.stream(input).map(Integer::parseInt).sorted().collect(Collectors.toList());
                validateNumberSize(numbers);
                validateNumberDuplicate(numbers);
                validateNumberRange(numbers);
                return numbers;
            }
            catch (NumberFormatException e) {
                System.out.println("[ERROR] 문자가 포함되어 있습니다. 1~45 사이의 숫자 6개를 입력하세요.");
            }
            catch (IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public Integer InputWinNumberBonus(List<Integer> numbers) {
        while (true) {
            System.out.println("보너스 번호를 입력해 주세요.");
            try {
                Integer input = Integer.parseInt(Console.readLine());
                validateBonusNumberRange(input);
                validateBonusNumberDuplicate(numbers, input);
                return input;
            }
            catch (NumberFormatException e) {
                System.out.println("[ERROR] 문자가 포함되어 있습니다. 1~45 사이의 숫자 1개를 입력하세요.");
            }
            catch (IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void validateAmount(int amount) {
        if (amount % 1000 != 0 || amount <= 0) {
            throw new IllegalStateException("[ERROR] 구입금액은 1,000원으로 나누어 떨어져야 합니다. 1,000원 단위의 금액을 입력해주세요.");
        }
    }

    public void validateNumberSize(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalStateException("[ERROR] 숫자가 6개가 아닙니다. 1~45 사이의 숫자 6개를 입력하세요.");
        }
    }

    public void validateNumberDuplicate(List<Integer> numbers) {
        List<Integer >uniqueNumbers = numbers.stream().distinct().toList();
        if (numbers.size() != uniqueNumbers.size()) {
            throw new IllegalStateException("[ERROR] 중복된 숫자가 존재합니다. 1~45 사이의 숫자 6개를 입력하세요.");
        }
    }

    public void validateNumberRange(List<Integer> numbers) {
        for (Integer num : numbers) {
            if (num < 1 || num > 45) {
                throw new IllegalStateException("[ERROR] 범위를 벗어나는 숫자가 존재합니다. 1~45 사이의 숫자 6개를 입력하세요.");
            }
        }
    }

    public void validateBonusNumberRange(Integer number) {
        if (number < 1 || number > 45) {
            throw new IllegalStateException("[ERROR] 범위를 벗어나는 숫자가 존재합니다. 1~45 사이의 숫자 1개를 입력하세요.");
        }
    }

    public void validateBonusNumberDuplicate(List<Integer> numbers, Integer number) {
        if (numbers.contains(number)) {
            throw new IllegalStateException("[ERROR] 중복된 숫자가 존재합니다. 1~45 사이의 숫자 1개를 입력하세요.");
        }
    }
}
