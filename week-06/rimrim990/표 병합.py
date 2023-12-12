def solution(commands):
  gr = Graph()
  for cmd in commands:
    cmd = cmd.split()
    ins = cmd[0]

    if ins == "UPDATE":
      if len(cmd) == 4:
        args = parse_args(cmd[1:3])
        gr.update_cell(*args, cmd[3])
        continue

      gr.update_all(*cmd[1:])

    elif ins == "MERGE":
      args = parse_args(cmd[1:])
      gr.merge(*args)

    elif ins == "UNMERGE":
      args = parse_args(cmd[1:])
      gr.unmerge(*args)

    elif ins == "PRINT":
      args = parse_args(cmd[1:])
      gr.print_value(*args)

  return gr.get_answer()

def parse_args(args):
  return list(map(int, args))

class Graph:
  merged_view = {}
  print_result = []

  def __init__(self):
    self.n = 51
    self.cell = [["" for _ in range(self.n)] for _ in  range(self.n)]
    self.merged_view = {}

    for y in range(self.n):
      for x in range(self.n):
        self.merged_view[(y, x)] = (y, x)

  def update_cell(self, r, c, val):
    mpos = self.merged_view[(r,c)]
    self.cell[mpos[0]][mpos[1]] = val

  def update_all(self, val1, val2):
    for y in range(self.n):
      for x in range(self.n):
        if self.cell[y][x] == val1:
          self.cell[y][x] = val2

  def merge(self, r1, c1, r2, c2):
    mpos = self.merged_view[(r1, c1)]
    mval = self.cell[mpos[0]][mpos[1]]
    target = self.merged_view[(r2, c2)]

    # (r1, c1)의 값이 없으므로 (r2, c2)의 값 사용
    if not mval:
      mpos = self.merged_view[(r2, c2)]
      mval = self.cell[mpos[0]][mpos[1]]
      target = self.merged_view[(r1, c1)]

    self.cell[target[0]][target[1]] = mval

    # target -> mpos 로 병합
    for cur in self.merged_view:
      if self.merged_view[cur] == target:
        self.merged_view[cur] = mpos

  def unmerge(self, r, c):
    mpos = self.merged_view[(r, c)]
    mval = self.cell[mpos[0]][mpos[1]]

    for cur in self.merged_view:
      if self.merged_view[cur] == mpos:
        self.cell[cur[0]][cur[1]] = ""
        self.merged_view[cur] = cur

    # 병합을 해제하기 전에 값이 있었으면 가져간다
    self.cell[r][c] = mval

  def print_value(self, r, c):
    mpos = self.merged_view[(r,c)]
    mval = self.cell[mpos[0]][mpos[1]]
    self.print_result.append(mval if mval else "EMPTY")

  def get_answer(self):
    return self.print_result