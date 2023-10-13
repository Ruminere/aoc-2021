file = open("input.txt", "r")
input = file.readlines()

def part1(input):
    horizontal = 0;
    depth = 0;
    for i in range(0, len(input)):
        data = input[i].split()
        if (data[0] == "forward"):
            horizontal += int(data[1])
        elif (data[0] == "up"):
            depth -= int(data[1])
        elif (data[0] == "down"):
            depth += int(data[1])
    return (horizontal * depth)

def part2(input):
    horizontal = 0;
    depth = 0;
    aim = 0;
    for i in range(0, len(input)):
        data = input[i].split()
        if (data[0] == "forward"):
            horizontal += int(data[1])
            depth += (int(data[1]) * aim)
        elif (data[0] == "up"):
            aim -= int(data[1])
        elif (data[0] == "down"):
            aim += int(data[1])
    return (horizontal * depth)

print("Part 1: " + str(part1(input)) )
print("Part 2: " + str(part2(input)) )
