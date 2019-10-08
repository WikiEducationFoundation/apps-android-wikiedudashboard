[Back to README](README.md)

## Contributing

We love contributions! If you're looking for a way to get involved, contact
Sage Ross (`ragesoss`). Our main venue for collaboration is Slack; ask Sage (sage at wikiedu.org) for an invite.

#### Bugs and enhancement issues

The dashboard app uses GitHub issues for bug tracking. Our main categories of bugs
organize problems by their importance (not how complex they will be to fix):
* [minor bug](https://github.com/WikiEducationFoundation/WikiEduDashboard/labels/minor%20bug) - These are bugs or usability problems that do not fundamentally break the dashboard app user experience, or that only affect edge cases. It's great to fix these, but they won't necessarily be prioritized any time soon (if ever).
* [bug](https://github.com/WikiEducationFoundation/WikiEduDashboard/labels/bug) - These are bugs that should definitely be fixed. They may not require immediate attention, but they significantly affect some aspect of dashboard functionality.
* [high-importance bug](https://github.com/WikiEducationFoundation/WikiEduDashboard/labels/high-importance%20bug) - These are big problems. They should fixed as soon as possible.

Possible improvements that are not bugs per-se — mostly ones that are independent, rather than complex feature requests — are also tracked:
* [enhancement](https://github.com/WikiEducationFoundation/WikiEduDashboard/issues?utf8=%E2%9C%93&q=is%3Aissue+is%3Aopen+label%3Aenhancement) - User-facing improvements that address some usability or functionality need
* [code quality](https://github.com/WikiEducationFoundation/WikiEduDashboard/issues?q=is%3Aissue+is%3Aopen+label%3A%22code+quality%22) - Developer-facing improvements that make the dashboard easier to work with or otherwise improve the quality of the code-base.

If you're a new developer and you're looking for an easy way to get involved, try one of the bugs tagged as newcomer friendly:
* [newcomer friendly](https://github.com/WikiEducationFoundation/WikiEduDashboard/issues?q=is%3Aissue+is%3Aopen+label%3A%22newcomer+friendly%22) - These range from very simple to moderately complex, but won't require you to understand the entire application or make extensive changes. We try to keep a few of these open for "microcontributions" for Outreachy applicants and others looking for an easy-to-intermediate task. If you can't find one you like, ask Sage!

#### Developer Resources

- [MediaWiki API Manual](https://www.mediawiki.org/wiki/Manual:Contents)
- [MediaWiki API Sandbox](https://en.wikipedia.org/wiki/Special%3aApiSandbox)

#### Code Style
TBA

#### Tests
TBA

#### Translations
TBA

## Pull request process

### Before opening a PR
- Create a git branch for the issue you are working on, rather than working directly on `master`.
- Rebase your work onto the latest version from master and fix any merge conflicts. (See [git.md](docs/git.md) for common Git procedures.)
- Check that only the relevant files are being changed. There should be no unintended changes to the gradle files, or other "git noise".
- If you have a messy git history, squash your commits to clean it up.

### PR format
- Title your pull request to indicate its purpose. (This will likely be similar to the title of the issue it addresses.)
- Include a reference to the issue(s).
- If it is a work-in-progress, include `[WIP]` in the title. Remove this once the PR is complete and ready for review.
- Include before-and-after screenshots (or animations) for user interface changes.

### After opening a PR
- Check the travis-ci continuous integration build once it completes. This usually takes about 10 minutes, and if any tests fail you can find details in the build log.
- If the build failed:
  - Fix whatever caused it to fail if you can.
  - Ask for help if you cannot fix it. If a pull request build is failing but you have not asked for help, we will assume that you have seen the errors and are still working on them.
- If you don't get reviewer feedback within a day or two, or you're waiting for followup, ping someone.
