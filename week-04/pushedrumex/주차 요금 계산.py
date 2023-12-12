from collections import defaultdict
from math import ceil

def solution(fees, records):
    basic_time, basic_fare, unit_time, unit_fare = fees
    time_dic = defaultdict(int)
    parking_dic = {}

    for record in records:
        time, number, info = record.split()
        if info == "IN":
            parking_dic[number] = convert_to_minute(time)
        elif info == "OUT":
            time_dic[number] += convert_to_minute(time) - parking_dic.pop(number)
    
    for number in parking_dic.keys():
        time_dic[number] += convert_to_minute("23:59") - parking_dic[number]
    
    answer = []
    for number in sorted(time_dic.keys()):
        fare = basic_fare
        rest = time_dic[number] - basic_time
        if rest > 0:
            fare += ceil(rest / unit_time) * unit_fare
        answer.append(fare)

    return answer

def convert_to_minute(s):
    h, m = map(int, s.split(":"))
    return h * 60 + m