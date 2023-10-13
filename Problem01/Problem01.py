input = open("input.txt", "r")
numsStr = input.readlines()
nums = [int(i) for i in numsStr]

def findGreaterSingle(nums):
    result = 0
    for i in range(1, len(nums)):
        if (nums[i] > nums[i-1]):
            result += 1
    return result

def findGreaterTriple(nums):
    result = 0
    sum1 = 0;
    sum2 = 0;
    for i in range(3, len(nums)):
        sum1 = nums[i-3] + nums[i-2] + nums[i-1]
        sum2 = nums[i-2] + nums[i-1] + nums[i]
        if (sum2 > sum1):
            result += 1;
    return result

print("Part 1: " + str(findGreaterSingle(nums)) )
print("Part 2: " + str(findGreaterTriple(nums)) )
