package lotto;

import camp.nextstep.edu.missionutils.Console;

public class LottoInput {
    public Integer InputAmount() {
        while (true) {
            System.out.println("구입금액을 입력해 주세요.");
            try {
                String input = Console.readLine();
                int amount = Integer.parseInt(input);
                validateAmount(amount);
                return amount;
            }
            catch (NumberFormatException e) {
                System.out.println("[ERROR] 구입금액에 문자는 입력할 수 없습니다. 1,000원 단위의 금액을 입력해주세요.");
            }
            catch (IllegalStateException e) {
                System.out.println("[ERROR] 구입금액은 1,000원으로 나누어 떨어져야 합니다. 1,000원 단위의 금액을 입력해주세요.");
            }
        }
    }

    public void validateAmount(int amount) {
        if (amount % 1000 != 0 || amount <= 0) {
            throw new IllegalStateException();
        }
    }
}
