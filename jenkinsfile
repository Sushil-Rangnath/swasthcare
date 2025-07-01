pipeline {
    agent any

    tools {
        maven 'Maven 3.8.5'  // Make sure this is installed in Jenkins
    }

    environment {
        DEPLOY_DIR = "/home/ubuntu/backend"  // or any path you choose
    }

    stages {


        stage('Build') {
            steps {
                sh './mvnw clean package -DskipTests'
            }
        }

        stage('Deploy') {
            steps {
                echo "Deploying .jar to backend directory"
                sh '''
                    mkdir -p $DEPLOY_DIR
                    cp target/*.jar $DEPLOY_DIR/app.jar
                    chmod +x $DEPLOY_DIR/app.jar
                '''
            }
        }

        stage('Restart App') {
            steps {
                echo "Restarting Spring Boot backend"
                sh '''
                    if pgrep -f "app.jar"; then
                        pkill -f "app.jar"
                    fi
                    nohup java -jar $DEPLOY_DIR/app.jar > $DEPLOY_DIR/app.log 2>&1 &
                '''
            }
        }
    }
}
