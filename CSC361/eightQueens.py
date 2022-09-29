import random
from math import exp

def initialize(N=8): #random initialization
    return [random.randint(1, N) for _ in range(N)]

def heuristic(state, N=8): # attacking queens heuristic function
    cost = 0
    for i in range(N):
        for j in range(i+1, N):
            if state[i] == state[j] or abs(j-i) == abs(state[j]-state[i]):
                cost +=1
    return cost

def printTable(state, N=8):
    table = [['-' for _ in range(N)] for _ in range(N)]
    for column, row in enumerate(state):
        table[row-1][column] = 'Q'
    for i in range(N):
        for j in range(N):
            print(table[i][j], end='  ')
        print()

def successors(state, N=8): # returns all successors
    Successors = []
    for i in range(N):
        for j in range(1, N+1):
            newState = state.copy()
            newState[i] = j if newState[i] != j else 0
            if 1 <= newState[i] <= N:
                Successors.append(newState)
    return Successors

# Hill Climbing Algorithms

def firstsuccessor(state):
    states = successors(state)
    for childstate in states:
        if heuristic(childstate) <= heuristic(state):
            return childstate
    return states[0]

def minsuccessor(state):
    states = successors(state)
    statesWithCost = [(state, heuristic(state)) for state in states]
    return min(statesWithCost, key = lambda x : x[1])[0]

def hillClimbing(initState, ChooseSuccessor=minsuccessor):
  
    if heuristic(initState) == 0:
        return initState, True
    
    prevState = initState
    while True:
        state = ChooseSuccessor(prevState)
        if heuristic(state) >= heuristic(prevState):
            return prevState, False
        elif heuristic(state) == 0:
            return state, True
        prevState = state

def randomRestart(initState):
    while True:
        solution = hillClimbing(initState)
        if solution[1]:
            return solution[0]
        initState = initialize()

# Simulated Annealing Algorithm

def simAnnealing(initState):

    def randomSuccessor(state):
        states = successors(state)
        return random.choice(states)
    
    current = initState
    Temperatue = 100
    cool = 0.98
    while True:
        Temperatue *= cool
        if heuristic(current) == 0:
            return current
        nextState = randomSuccessor(current)
        deltaE = heuristic(nextState) - heuristic(current)
        if deltaE < 0:
            current = nextState
        else:
            threshhold = exp(-deltaE/Temperatue)
            prob = random.random()
            if prob < threshhold:
                current = nextState
            if heuristic(current) == 0:
              return current

# Tests

def main():
    initState = initialize()
    print('Initial State:')
    printTable(initState)
    print(f'cost: {heuristic(initState)}')

    solution = hillClimbing(initState)
    if solution[1]:
        print('Steepest Ascent solution: ')
        printTable(solution[0])
        print(f'cost: {heuristic(solution[0])}')
    else:
        print('Steepest Ascent stuck in local minima: ')
        printTable(solution[0])
        print(f'cost: {heuristic(solution[0])}')

    solution = hillClimbing(initState, firstsuccessor)
    if solution[1]:
        print('First Choice solution: ')
        printTable(solution[0])
        print(f'cost: {heuristic(solution[0])}')
    else:
        print('First Choice stuck in local minima: ')
        printTable(solution[0])
        print(f'cost: {heuristic(solution[0])}')

    solution = randomRestart(initState)
    print('Random Restart solution: ')
    printTable(solution)
    print(f'cost: {heuristic(solution)}')

    solution = simAnnealing(initState)
    print('simulated Annealing solution: ')
    printTable(solution)
    print(f'cost: {heuristic(solution)}')

if __name__ == '__main__':
    main()