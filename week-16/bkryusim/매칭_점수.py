import re


def parse_meta(page):
    searched = re.compile('<meta property="og:url" content="https://\S+"').search(page)

    res = page[searched.start():searched.end()].split('=')[-1]
    res = res[1:-1]
    return res


def parse_a(page):
    searched = re.compile('<a href="https://\S+"').findall(page)
    res = []

    for tag in searched:
        link = tag.split('=')[-1]
        res.append(link[1:-1])
    return res


def parse_keyword(page, keyword):
    keyword = keyword.lower()
    tokens = re.sub('[^a-zA-Z]', ' ', page).split()
    count = 0

    for token in tokens:
        if keyword == token.lower():
            count += 1

    return count


def solution(word, pages):
    scores = list(map(lambda x: parse_keyword(x, word), pages))
    external_score = [0] * len(pages)
    d = {}

    for i, page in enumerate(pages):
        url = parse_meta(page)
        d[url] = i

    for i, links in enumerate(list(map(parse_a, pages))):
        count = len(links)

        for link in links:
            if link in d.keys():
                external_score[d[link]] += scores[i] / count

    result = [(scores[i] + external_score[i], -i) for i in range(len(pages))]
    result.sort()

    return -result[-1][1]
