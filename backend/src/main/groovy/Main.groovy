import server.TomcatServer
import utils.Utils
import controller.MenuActions
import view.MenuCRUD

// Linketinder - Alex Benjamim de Oliveira Martins (2025)

Thread tomcatServer = new Thread(new TomcatServer())
tomcatServer.start()

MenuActions menuActions = new MenuActions()

println "===MENU===\n[1]Menu Crud"
printf "> "
def choice = Utils.readInt()

if (choice == 1) {
    MenuCRUD.showMenu(menuActions)
} else {
    println "Escolha invalida"
    return
}
