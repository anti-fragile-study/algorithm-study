not_allowed = '~!@#$%^&*()=+[{]}:?,<>/'

def solution(new_id):
  # 1단계
  new_id = new_id.lower()
  # 2단계
  for c in not_allowed:
    new_id = new_id.replace(c, '')
  # 3단계
  while '..' in new_id:
    new_id = new_id.replace('..', '.')
  # 4단계
  new_id = strip_comma(new_id)

  # 5단계
  if len(new_id) == 0:
    new_id += 'a'
  # 6단계
  if len(new_id) >= 16:
    new_id = strip_comma(new_id[:15])
  # 7단계
  while len(new_id) <= 2:
    new_id += new_id[-1]

  return new_id

def strip_comma(str):
  return str.strip('.')
