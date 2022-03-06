$ git clone https://github.com/StanislavBB/KotlinAsFirst2020

$ git remote add upstream-my https://github.com/StanislavBB/KotlinAsFirst2021

$ git fetch upstream-my

$ git rebase --onto master 1137b420cc95fa6894edad69b31e2da1bb985d1d upstream-my/master

$ git branch backport

$ git checkout master

$ git merge backport

$ git remote add upstream-theirs https://github.com/qvchoq/KotlinAsFirst2021

$ git fetch upstream-theirs

$ git merge -s ours upstream-theirs/master

$ git remote -v > remotes

$ git add remotes

$ git commit -m "Add remotes file"

$ touch howto.md

$ git add howto.md

$ git commit -m "Add howto.md"

$ git push

$ git checkout backport

$ git push --set-upstream origin backport