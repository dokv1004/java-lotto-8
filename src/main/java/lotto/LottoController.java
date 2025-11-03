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
        printResult(lottoNumbers, winNumber);
    }

    public List<Lotto> getLottoNumber(int lottoCnt) {
        System.out.println();
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

    private double rewardResult(Map<LottoRanking, Integer> result) {
        double rateOfReturn = 0;
        double totalCost = lottoCnt * 1000;
        double winReward = 0;
        for (LottoRanking rank : result.keySet()) {
            winReward = winReward + (rank.getReward() * result.get(rank));
        }
        rateOfReturn = (winReward / totalCost) * 100;
        return rateOfReturn;
    }

    public void printResult(List<Lotto> lottoNumbers, List<Integer> win) {
        Map<LottoRanking, Integer> result = lottoResult(lottoNumbers, win);
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---");
        for (int i = LottoRanking.values().length - 2; i >= 0; i--) {
            LottoResult(LottoRanking.values()[i].getMsg(), result.get(LottoRanking.values()[i]));
        }
        System.out.println("총 수익률은 " + String.format("%.1f", rewardResult(result)) + "%입니다.");
    }

    public void LottoResult(String msg, int matchAmount) {
        System.out.println(msg + matchAmount + "개");
    }
}
