# homepage-application-service

Homepage application is a backend service providing basic apis for my personal homepage.

The frontend repository is [hongwei-bai/react-homepage-demo](https://github.com/hongwei-bai/react-homepage-demo).

Homepage application only provides services can be accessed by public token.

Those services need finer controlled access (e.g. blog, albums etc.) would be provided by other applications.

Public access token is generated with same secrets and shared across all frontend clients.(web/Android/iOS)

#### Related projects

Frontend(Web) repository:
[hongwei-bai/react-homepage-demo](https://github.com/hongwei-bai/react-homepage-demo).

Authentication service(backend) repository:
[hongwei-bai/application-service-authentication](https://github.com/hongwei-bai/application-service-authentication)

#### API sample

|||
| ------------- |-------------|
| `METHOD` |`GET`                   |
| `PATH` | **/covid19/auBrief.do?days=1&dataVersion=1&top=2&followedSuburbs=2118,2075,2122** |

A sample response is like:

```
{
    "dataVersion": 2021092118,
    "dataByDay": [
        {
            "dayDiff": 2,
            "dateDisplay": "2021-09-20",
            "caseByState": [
                {
                    "stateCode": "NSW",
                    "stateName": "New South Wales",
                    "cases": 898
                }
            ],
            "caseExcludeFromStates": 14,
            "caseTotal": 912,
            "caseByPostcodeTops": [
                {
                    "postcode": 2170,
                    "suburbBrief": "Liverpool",
                    "state": "NSW",
                    "cases": 65
                },
                {
                    "postcode": 2770,
                    "suburbBrief": "Mount Druitt",
                    "state": "NSW",
                    "cases": 51
                }
            ],
            "caseByPostcodeFollowed": [
                {
                    "postcode": 2122,
                    "suburbBrief": "Marsfield",
                    "state": "NSW",
                    "cases": 3
                },
                {
                    "postcode": 2118,
                    "suburbBrief": "Carlingford",
                    "state": "NSW",
                    "cases": 1
                },
                {
                    "postcode": 2075,
                    "suburbBrief": "St Ives",
                    "state": "NSW",
                    "cases": 1
                }
            ]
        }
    ]
}
```