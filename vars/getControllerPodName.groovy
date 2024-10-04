def call(logOutput) {
    script {
        // Introducing a small sleep to ensure logs are populated (if timing is an issue)
        //sleep(5)

        // Extract the log content from the current build
        //def logOutput = currentBuild.rawBuild.getLog()  // Fetch the logs
        //echo "First log lines: ${logOutput}"

        // Use regex or split to extract the controller pod name
        def matcher = (logOutput =~ /Managed by (.+)/)
        if (matcher) {
            def controllerPodName = matcher[0][1]  // Capture the pod name from the log
            echo "Controller Pod Name extracted: ${controllerPodName}"
            return controllerPodName  // Return the controller pod name
        } else {
            echo "Could not find the controller pod name in the logs."
            return null  // Return null if no match is found
        }
    }
}
