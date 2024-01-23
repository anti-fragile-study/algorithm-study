# 기본 점수: 검색어가 등장하는 횟수 (대소문자 무시)
# 외부 링크 수: 링크의 개수
# 링크 점수: 링크가 걸린 웹페이지의 (기본 점수 / 외부 링크 수)의 합

# 매칭 점수 = 기본 점수 + 링크 점수
# 주소: <meta content="?">
# 외부 링크: <a href="?">

# 매칭 점수가 가장 높은 웹 페이지의 idx 중 가장 작은 것을 구하라

from collections import *
import re

def solution(word, pages):
    # 주소: idx
    # idx: [주소, 기본 점수, 외부 링크 주소들]
    idx_by_url, info_by_idx = {}, {}

    # 검색어 소문자로
    word = word.lower()

    # page들에 대하여
    for idx, page in enumerate(pages):
        # 주소 찾기
        url = re.findall('<meta\s+[^>]*content="(.*?)"/>', page)[0]
        idx_by_url[url] = idx

        # 검색어 찾기
        words = re.findall('[a-zA-Z]+', page)
        word_count = len(list(filter(lambda w : w.lower() == word, words)))

        # 외부 링크 찾기
        ext_links = re.findall('<a\s+[^>]*href="(.*?)">', page)
        info_by_idx[idx] = [url, word_count, ext_links]

    scores = [0 for _ in range(len(info_by_idx))]
    for idx, val in info_by_idx.items():
        scores[idx] += val[1] # 기본 점수

        for ext_link in val[2]:
            if not ext_link in idx_by_url:
                continue

            scores[idx_by_url[ext_link]] += val[1] / len(val[2])

    return max(range(len(info_by_idx)), key = lambda idx : scores[idx])
