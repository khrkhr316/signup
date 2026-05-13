document.addEventListener("DOMContentLoaded", () => {

    const form = document.getElementById("form1");

    const password =
        document.getElementById("password");

    const mobileRuleSpecial =
        document.getElementById("mobile-rule-special");

    const mobileRuleLetter =
        document.getElementById("mobile-rule-letter");

    const mobileRuleLength =
        document.getElementById("mobile-rule-length");

    password.addEventListener("input", () => {

        const passwordValue = password.value;

        const hasSpecialCharacter =
            /[!@#$%^&*(),.?":{}|<>]/.test(passwordValue);

        const hasLetter =
            /[a-zA-Z]/.test(passwordValue);

        const hasMinLength =
            passwordValue.length >= 12;

        // 특수문자
        mobileRuleSpecial.classList.toggle(
            "valid",
            hasSpecialCharacter
        );

        mobileRuleSpecial.classList.toggle(
            "invalid",
            !hasSpecialCharacter
        );

        // 알파벳
        mobileRuleLetter.classList.toggle(
            "valid",
            hasLetter
        );

        mobileRuleLetter.classList.toggle(
            "invalid",
            !hasLetter
        );

        // 길이
        mobileRuleLength.classList.toggle(
            "valid",
            hasMinLength
        );

        mobileRuleLength.classList.toggle(
            "invalid",
            !hasMinLength
        );
    });

    const passwordCheck =
        document.getElementById("password-check");

    const emailInput =
        document.getElementById("email");

    const checkEmailButton =
        document.getElementById("check-email");

    const ruleSpecial =
        document.getElementById("rule-special");

    const ruleLetter =
        document.getElementById("rule-letter");

    const ruleLength =
        document.getElementById("rule-length");

    let isEmailValid = false;

    /* 실시간 비밀번호 조건 검사 */
    password.addEventListener("input", () => {

        const value = password.value;

        // 특수문자 포함 여부
        const hasSpecial =
            /[!@#$%^&*(),.?":{}|<>]/.test(value);

        // 영문 포함 여부
        const hasLetter =
            /[a-zA-Z]/.test(value);

        // 길이 검사
        const hasLength =
            value.length >= 12;

        updateRule(ruleSpecial, hasSpecial);

        updateRule(ruleLetter, hasLetter);

        updateRule(ruleLength, hasLength);
    });

    /* 조건 상태 변경 함수 */
    function updateRule(element, valid) {

        if (valid) {

            element.classList.remove("invalid");

            element.classList.add("valid");

            element.innerHTML =
                element.innerHTML.replace("✖", "✔");

        } else {

            element.classList.remove("valid");

            element.classList.add("invalid");

            element.innerHTML =
                element.innerHTML.replace("✔", "✖");
        }
    }

    // 이메일 중복 체크
    checkEmailButton.addEventListener("click", () => {

        const email = emailInput.value.trim();

        // 이메일 입력 안 했을 때
        if (email === "") {

            alert("이메일을 입력하세요");

            isEmailValid = false;

            return;
        }

        fetch("/check-email", {

            method: "POST",

            headers: {
                "Content-Type":
                    "application/x-www-form-urlencoded"
            },

            body: new URLSearchParams({ email })

        })

            .then(response => response.json())

            .then(data => {

                // true = 이미 존재
                if (data) {

                    alert("이미 사용된 이메일입니다");

                    isEmailValid = false;

                } else {

                    alert("사용 가능한 이메일입니다");

                    isEmailValid = true;
                }
            })

            .catch(error => {

                console.error('Email check failed:', error);

                alert("이메일 중복 체크 실패");
            });
    });

    // 회원가입 submit
    form.addEventListener("submit", (e) => {

        const passwordValue =
            password.value;

        const passwordCheckValue =
            passwordCheck.value;

        // 특수문자
        const hasSpecialCharacter =
            /[!@#$%^&*(),.?":{}|<>]/.test(passwordValue);

        // 영문 포함
        const hasLetter =
            /[a-zA-Z]/.test(passwordValue);

        // 12자 이상
        const hasMinLength =
            passwordValue.length >= 12;

        // 이메일 중복 체크 여부
        if (!isEmailValid) {

            e.preventDefault();

            alert("이메일 중복체크를 해주세요");

            return;
        }

        // 비밀번호 확인
        if (passwordValue !== passwordCheckValue) {

            e.preventDefault();

            alert("비밀번호가 일치하지 않습니다");

            return;
        }

        // 특수문자 검사
        if (!hasSpecialCharacter) {

            e.preventDefault();

            alert("특수문자를 1개 이상 포함해주세요");

            return;
        }

        // 영문 검사
        if (!hasLetter) {

            e.preventDefault();

            alert("알파벳을 포함해주세요");

            return;
        }

        // 길이 검사
        if (!hasMinLength) {

            e.preventDefault();

            alert("비밀번호는 12자 이상이어야 합니다");

            return;
        }

    });

});