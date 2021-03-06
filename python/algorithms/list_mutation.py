from sys import stdin


def process():
    arr = list(map(str, input().split()))
    for line in stdin:
        command = list(map(str, line.split()))
        if command == ['end']:
            break

        if command[1] == 'from':
            start_idx = int(command[2])
            count = int(command[4])
            if command[0] == 'reverse':
                arr = reverse_sublist(arr, start=start_idx, count=count)
            elif command[0] == 'sort':
                arr = sort_sublist(arr, start=start_idx, count=count)
        elif command[0] == 'remove':
            start_idx = int(command[1])
            arr = remove_from_start(arr, start_idx)

    print(', '.join(arr))


def reverse_sublist(lst, start, count):
    end = start + count
    lst[start:end] = lst[start:end][::-1]
    return lst


def remove_from_start(lst, start):
    return lst[start:]


def sort_sublist(lst, start, count):
    end = start + count
    lst[start:end] = sorted(lst[start:end])
    return lst


if __name__ == '__main__':
    process()

# Input:
#
# 1 2 5 8 7 3 10 6 4 9
# reverse from 2 count 4
# end
#
# Output:
# 1, 2, 3, 7, 8, 5, 10, 6, 4, 9
#
# Input:
#
# 1 2 5 8 7 3 10 6 4 9
# sort from 2 count 4
# end
#
# Output:
# 1, 2, 3, 5, 7, 8, 10, 6, 4, 9
#
# Input:
#
# 1 2 5 8 7 3 10 6 4 9
# remove 2
# end
#
# Output:
# 5, 8, 7, 3, 10, 6, 4, 9
