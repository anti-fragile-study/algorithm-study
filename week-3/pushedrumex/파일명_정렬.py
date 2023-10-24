def isNumeric(s):
    return "0" <= s <= "9"

def getNumber(s):
    result = ""
    for i in range(len(s)):
        if isNumeric(s[i]):
            while i < len(s) and isNumeric(s[i]):
                result += s[i]
                i += 1
            break

    return int(result)

def getHead(s):
    s = s.upper()
    result = ""
    i = 0
    while i < len(s) and not isNumeric(s[i]):
        result += s[i]
        i += 1
        
    return result

def solution(files):
    return sorted(files, key = lambda x: (getHead(x), getNumber(x)))