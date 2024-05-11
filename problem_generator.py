import requests
import random
from bs4 import BeautifulSoup

def select_and_add_problems(size, url):
    data = requests.get(url).json()
    random_problems = random.sample(data["result"], size)
    for problem in random_problems:
        problems.append({"url": "https://school.programmers.co.kr/learn/courses/30/lessons/" + str(problem["id"]), "title": problem["title"]}) 
 
problems = []

# level 1
select_and_add_problems(1, "https://school.programmers.co.kr/api/v2/school/challenges/?perPage=20&levels[]=1&languages[]=java&order=recent&search=&page=1&perPage=1000")

# level 2
select_and_add_problems(2, "https://school.programmers.co.kr/api/v2/school/challenges/?perPage=20&levels[]=2&languages[]=java&order=recent&search=&page=1&perPage=1000")

# level 3,4
select_and_add_problems(2, "https://school.programmers.co.kr/api/v2/school/challenges/?perPage=20&levels[]=3&levels[]=4&languages[]=java&order=recent&search=&page=1&perPage=1000")

with open("README.md", "r", encoding="utf-8") as file:
        readme = file.read()

# 새로운 테이블 행 추가
week = int(BeautifulSoup(readme, 'html.parser').find_all('tr')[1].find_all('td')[0].get_text().split("주차")[0])
new_rows = "<tr>\n\t<td>" + str(week+1) + "주차</td>\n"
for problem in problems:
    new_rows += "\t<td><a href=" + problem['url'] + ">" + problem['title'] + "</a></td>\n"
new_rows += "</tr>\n"

# 테이블에 새로운 행 추가
table_start_index = readme.find("</tr>")
table_end_index = readme.find("</table>") + len("</table>")
table_header_index = readme.find("<tr>", table_start_index, table_end_index)
updated_readme = readme[:table_header_index] + new_rows + readme[table_header_index:]

with open("README.md", "w", encoding="utf-8") as file:
    file.write(updated_readme)