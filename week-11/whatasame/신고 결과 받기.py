# 중복 신고 가능 -> 1회로 처리
# k번 이상시 신고 받은사람은 정지
# 신고를 한 사람에게 결과 메일 전송
# 신고를 배치 처리

# 신고자별 받은 메일 횟수

from collections import defaultdict


def solution(id_list, reports, k):
    # k: 신고 당한사람, v: set(신고 한 사람)
    statistics = defaultdict(set)
    idx_by_id = {id: idx for idx, id in enumerate(id_list)}

    for report in reports:
        reporter, reportee = report.split()
        statistics[reportee].add(reporter)

    answer = [0 for _ in range(len(id_list))]
    for reportee, reporters in statistics.items():
        if len(reporters) >= k:
            for reporter in reporters:
                answer[idx_by_id[reporter]] += 1

    return answer
