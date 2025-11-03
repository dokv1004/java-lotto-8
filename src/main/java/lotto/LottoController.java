package lotto;

import java.util.*;

public class LottoController {

    private int bonusNumber;
    private int lottoCnt;
    private List<Integer> winNumber;
    private List<Lotto> lottoNumbers = new ArrayList<>();
    LottoNumberGenerator lottoNumberGenerator = new LottoNumberGenerator();

    public void run() {
        LottoInput lottoInput = new LottoInput();
        lottoCnt = lottoInput.InputAmount();
        getLottoNumber(lottoCnt);
        winNumber = lottoInput.InputWinNumber();
        bonusNumber = lottoInput.InputWinNumberBonus(winNumber);

    }

    public List<Lotto> getLottoNumber(int lottoCnt) {
        System.out.println(lottoCnt + "개를 구매했습니다.");
        for (int i=0; i<lottoCnt; i++) {
            lottoNumbers.add(lottoNumberGenerator.generateLotto());
        }
        for (Lotto lotto : lottoNumbers) {
            List<Integer> numbers = new ArrayList<Integer>();
            numbers.addAll(lotto.getNumbers());
            Collections.sort(numbers);
            System.out.println(numbers);
        }
        return lottoNumbers;
    }

    public Map<LottoRanking, Integer> lottoResult(List<Lotto> lottoList, List<Integer> win) {
        Map<LottoRanking, Integer> result = new HashMap<>();
        for (LottoRanking rank : LottoRanking.values()) {
            result.put(rank, 0);
        }
        for (Lotto lotto : lottoList) {
            int count = 0;
            for (int number : win) {
                if (lotto.getNumbers().contains(number)) {
                    count++;
                }
            }
            LottoRanking rank = LottoRanking.valueOf(count, containBonusNumber(lotto.getNumbers()));
            result.put(rank, result.get(rank) + 1);
        }
        return result;
    }

    public boolean containBonusNumber(List<Integer> numbers) {
        return numbers.contains(bonusNumber);
    }
}
