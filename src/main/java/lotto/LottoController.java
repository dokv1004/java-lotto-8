package lotto;

import java.util.List;

public class LottoController {
    private int lottoCnt;
    private List<Integer> winNumber;

    public void run() {
        LottoInput lottoInput = new LottoInput();
        lottoCnt = lottoInput.InputAmount();
        winNumber = lottoInput.InputWinNumberBonus(lottoInput.InputWinNumber());
    }
}
