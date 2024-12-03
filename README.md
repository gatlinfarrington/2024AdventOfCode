## 2024 Advent of Code

Advent of Code is a series of programming challenges developed by [Eric Wastl](https://was.tl).

The solutions to each day will be found in `src/main/kotlin/Days`.

> [!NOTE]
> I will be trying my best to solve each problem, but life happens and I may not have time to do so. 

### Fetching the input
To automatically fetch the input, there is a script found at `src/fetch_input/sh`, in order to run this script, we must save our session cookie. 
Find your session cookie by inspecting element on the website, viewing cookies, and it should be there. 

You can save it as an environment variable by running:
```declarative
echo 'export AOC_SESSION=<Session Cookie>' >> ~/.bashrc
source ~/.bashrc
```

After doing this, you can run the script with an argument of the day you would like to fetch the input for as follows:
`./fetch_input.sh <day>`